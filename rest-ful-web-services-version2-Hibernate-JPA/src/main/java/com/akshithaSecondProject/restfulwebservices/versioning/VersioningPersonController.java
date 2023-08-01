package com.akshithaSecondProject.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	//------- Based on the the URL---------
	@GetMapping("/v1/person")
	public personV1 getFirstVersionOfPerson() {
		return new personV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public personV2 getSecondVersionOfPerson() {
		return new personV2(new Name("Bob","Charlie"));
	}
	
	//------- Based on the the parameter---------
	@GetMapping(path = "/person", params = "version=1")
	public personV1 getFirstVersionOfPersonRequestParameter() {
		return new personV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person", params = "version=2")
	public personV2 getSecondVersionOfPersonRequestParameter() {
		return new personV2(new Name("Bob","Charlie"));
	}
	
	//------- Based on the the Request Header---------
		@GetMapping(path = "/person/header", headers ="X-API-VERSION=1")
		public personV1 getFirstVersionOfPersonRequestHeader() {
			return new personV1("Bob Charlie");
		}
		
		@GetMapping(path = "/person/header",headers ="X-API-VERSION=2")
		public personV2 getSecondVersionOfPersonRequestHeader() {
			return new personV2(new Name("Bob","Charlie"));
		}
		
		//------- Based on the the media type---------
				@GetMapping(path = "/person/accept", produces ="application/vnd.company.app-v1+json")
				public personV1 getFirstVersionOfPersonAcceptHeader() {
					return new personV1("Bob Charlie");
				}
				
				@GetMapping(path = "/person/accept",produces ="application/vnd.company.app-v2+json")
				public personV2 getSecondVersionOfPersonAcceptHeader() {
					return new personV2(new Name("Bob","Charlie"));
				}
}
