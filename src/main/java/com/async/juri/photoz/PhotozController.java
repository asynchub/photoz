package com.async.juri.photoz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotozController {

    private static Map<String, Photo> db = new HashMap<>();
    static {
        db.put("1", new Photo("1", "hello.jpg"));
    }

//    private Map<String, Photo> db = new HashMap<String, Photo>() {{
//        put("1", new Photo("1", "hello.jpg"));
//    }};

    @GetMapping("/")
    public String hello() {
        return "Hello world.";
    }

    @GetMapping("/photoz")
    public Collection<Photo> get() {
        return db.values();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = db.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id) {
        Photo photo = db.remove(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/photoz")
    public Photo create(Photo photo) {
        photo.setId(UUID.randomUUID().toString());
        db.put(photo.getId(), photo);
        return photo;
    }
}
