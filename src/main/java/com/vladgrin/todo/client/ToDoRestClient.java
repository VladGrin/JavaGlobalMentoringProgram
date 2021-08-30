package com.vladgrin.todo.client;

import com.vladgrin.todo.client.domain.ToDo;
import com.vladgrin.todo.client.error.ToDoErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class ToDoRestClient {

    private final RestTemplate restTemplate;
    private final ToDoRestClientProperties prop;

    public ToDoRestClient(ToDoRestClientProperties properties) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setErrorHandler(new ToDoErrorHandler());
        this.prop = properties;
    }

    public Iterable<ToDo> findAll() throws URISyntaxException {
        RequestEntity<Iterable<ToDo>> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(prop.getUrl() + prop.getBasePath()));
        ResponseEntity<Iterable<ToDo>> response = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Iterable<ToDo>>() {
        });
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    public ToDo findBuId(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        try {
            return restTemplate.getForObject(prop.getUrl() + prop.getBasePath() + "/{id}", ToDo.class, params);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public ToDo upsert(ToDo toDo) throws URISyntaxException {
        RequestEntity<?> requestEntity = new RequestEntity<>(toDo, HttpMethod.POST, new URI(prop.getUrl() + prop.getBasePath()));
        ResponseEntity<?> response = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<ToDo>() {
        });
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return restTemplate.getForObject(Objects.requireNonNull(response.getHeaders().getLocation()), ToDo.class);
        }
        return null;
    }

    public void delete(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        restTemplate.delete(prop.getUrl() + prop.getBasePath() + "/{id}", params);
    }
}
