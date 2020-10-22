package com.agileengine.gallery.helper;

import com.agileengine.gallery.model.Image;
import com.agileengine.gallery.model.Response;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Map;

public class GalleryHelper {

    public static Response auth() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{ \"apiKey\": \"23567b218376f79d9415\" }";

        HttpEntity<Object> httpEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Response> exchange =
                restTemplate.exchange("http://interview.agileengine.com/auth",
                                        HttpMethod.POST,
                                        httpEntity,
                                        Response.class);
        return exchange.getBody();
    }

    public static Map getImages(String token) {
        Map result = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

        HttpEntity<Object> entity = new HttpEntity<>(headers);

        boolean hasMore = true;
        int page = 1;

        ResponseEntity<Object> exchange;

        while (hasMore) {
            exchange = restTemplate.exchange(
                    "http://interview.agileengine.com/images" + "?page=" + page,
                    HttpMethod.GET,
                    entity,
                    Object.class);

            if (exchange.getBody() != null) {
                Map body = (Map) exchange.getBody();

                result.put(body.get("hasMore"), body.get("pictures"));

                hasMore = (boolean) body.get("hasMore");
                page++;
            }
        }
        return result;
    }

    public static Image getInfo(String id, String token) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<Image> item =
               restTemplate.exchange(
                       "http://interview.agileengine.com/images/" + id,
                       HttpMethod.GET,
                       entity,
                       Image.class);

        return item.getBody();
    }

    public static Specification<Image> containsText(String searchTerm) {
        return (Specification<Image>) (root, query, builder) -> {

            String term = searchTerm;

            if (!searchTerm.contains("%")) {
                term = "%" + searchTerm + "%";
            }
            return getBuilder(builder).or(
                    getBuilder(builder).like(root.get("author"), term),
                    getBuilder(builder).like(root.get("camera"), term),
                    getBuilder(builder).like(root.get("tags"), term)
            );
        };
    }

    private static CriteriaBuilder getBuilder(CriteriaBuilder builder) {
        return builder;
    }}
