package com.learningspringboot.RESTfulApp.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Factors to consider:
 * 
 *  1) URI Pollution with versioning
 *  2) Headers were not supposed to be used to implement versioning
 *  3) Caching: When it comes to Headers versioning or Media Type versioning you
 *  cannot cache based on just the URL. You'll also need to look at Headers.
 *  4) Can we execite request on the browser. When it comes Headers versioning or
 *  Media Type versioning the differentiation is in the headers. Typically, you
 *  will require a client for this or command-line utility.
 *  5) Typically, for URI versioning and request parameter versioning documenting
 *  is easier because the URLs are different for both versions.
 *  6) Typically, API documenting tools may not document based on versioning
 *  differentiated on headers. So, Headers versioning or Media Type versioning
 *  might be a little difficult.
 */
@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getVersionedPerson1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getVersionedPerson2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
 
    @GetMapping(path = "/person/header", headers = "RANDOM-HEADER=1")
    public PersonV1 getHeaderedPerson1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/header", headers = "RANDOM-HEADER=2")
    public PersonV2 getHeaderedPerson2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person/accpet", produces = "application/vndtest")
    public PersonV2 getVerionedPersonAcceptHeader() {
        return new PersonV2(new Name("Test", "Charlie"));
    }
}
