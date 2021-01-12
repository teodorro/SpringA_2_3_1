package ru.netology.service;

import ru.netology.repository.PostRepository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.List;

public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all(){
        return repository.all();
    }

    public Post getById(long id) throws NotFoundException {
        return repository.getById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Post save(Post post) throws NotFoundException {
        return repository.save(post);
    }

    public void removeById(long id){
        repository.removeById(id);
    }
}
