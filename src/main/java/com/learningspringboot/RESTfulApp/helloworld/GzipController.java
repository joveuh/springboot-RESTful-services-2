
package com.learningspringboot.RESTfulApp.helloworld;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("g")
public class GzipController {

    @GetMapping(path = "get")
    public ResponseEntity<byte[]> downloadGzipFile(HttpServletRequest request,
            @RequestParam String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
            return ResponseEntity.badRequest().body(("File not found, you sent: " + filename).getBytes());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(file.length());
        headers.setContentDispositionFormData("attachment", file.getName());

        boolean gzip = false;
        byte[] compressed;

        String acceptEncoding = request.getHeader(HttpHeaders.ACCEPT_ENCODING);
        if (acceptEncoding != null && acceptEncoding.toLowerCase().contains("gzip")) {
            gzip = true;
        }
        try (FileInputStream is = new FileInputStream(file);
                ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            if (gzip) {
                headers.set(HttpHeaders.CONTENT_ENCODING, "gzip");
                try (GZIPOutputStream gos = new GZIPOutputStream(buffer)) {
                    byte[] temp = new byte[4096];
                    int read;
                    while ((read = is.read(temp)) != -1) {
                        gos.write(temp, 0, read);
                    }
                    gos.finish(); // Ensure GZIP stream is properly finalized
                }
            } else {
                byte[] temp = new byte[4096];
                int read;
                while ((read = is.read(temp)) != -1) {
                    buffer.write(temp, 0, read);
                }
            }

            compressed = buffer.toByteArray();
            headers.setContentLength(compressed.length); // Set the actual length of the compressed/uncompressed data

        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(("Error processing file: " + e.getMessage()).getBytes());
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(compressed);

    }
}
