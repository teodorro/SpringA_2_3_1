package ru.netology.controller;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> all(){
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) throws NotFoundException {
        return service.getById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post) throws NotFoundException {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id){
        service.removeById(id);
    }
}
