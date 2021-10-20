//package com.amatiservice.rest.inter.cors;
//
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerResponseContext;
//import javax.ws.rs.container.ContainerResponseFilter;
//import javax.ws.rs.ext.Provider;
//
//@Provider
//public class CORSFilter implements ContainerResponseFilter {
//
//    @Override
//    public void filter(ContainerRequestContext request,
//                       ContainerResponseContext response) {
//
//        System.out.println("Request XX: " +  request);
//        System.out.println("Method XX: " +  request.getMethod());
//        System.out.println("Media type XX: " +  request.getMediaType());
//
//        request.getHeaders().add("Access-Control-Allow-Origin", "*");
//        request.getHeaders().add("Access-Control-Allow-Headers",
//                "origin, content-type, accept, authorization");
//        request.getHeaders().add("Access-Control-Allow-Credentials", "true");
//        request.getHeaders().add("Access-Control-Allow-Methods",
//                "GET, POST, HEAD");
//
//        response.getHeaders().add("Access-Control-Allow-Origin", "*");
//        response.getHeaders().add("Access-Control-Allow-Headers",
//                "origin, content-type, accept, authorization");
//        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
//        response.getHeaders().add("Access-Control-Allow-Methods",
//                "GET, POST, HEAD");
//    }
//}