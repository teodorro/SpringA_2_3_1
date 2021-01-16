package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private long nextId = 5;
    private List<Post> repository = new ArrayList<>();
    private Lock lock = new ReentrantLock();


    public List<Post> all(){
        List<Post> posts = Collections.emptyList();

        lock.lock();
        try {
            posts = repository.stream().filter(x -> !x.getRemoved()).collect(Collectors.toList());
        } finally {
            lock.unlock();
        }
        System.out.println("get all");
        return posts;
    }

    public Optional<Post> getById(long id){
        Optional<Post> post = null;

        lock.lock();
        try {
            post = repository.stream().filter(x -> x.getId() == id && !x.getRemoved()).findAny();
        } finally {
            lock.unlock();
        }
        System.out.println("get post id=" + id);
        return post;
    }

    public Post save(Post post) throws NotFoundException {
        if (post.getId() == 0) {
            lock.lock();
            try {
                post = new Post(nextId++, post.getMessage());
                repository.add(post);
            } finally {
                lock.unlock();
            }
        }
        else{
            lock.lock();
            try {
                var postId = post.getId();
                var item = repository.stream().filter(x -> x.getId() == postId && !x.getRemoved()).findAny();
                if (item.isEmpty()){
                    throw new NotFoundException(postId);
                } else{
                    item.get().setMessage(post.getMessage());
                }
            } finally {
                lock.unlock();
            }
        }

        System.out.println("post id=" + post.getId() + " saved");
        return post;
    }

    public void removeById(long id) throws NotFoundException {
        lock.lock();
        try {
            var post = repository.stream().filter(x -> x.getId() == id && !x.getRemoved()).findAny();
            if (post.isPresent()){
                post.get().setRemoved(true);
            }
            else{
                throw new NotFoundException(id);
            }
        } finally {
            lock.unlock();
        }
        System.out.println("post id=" + id + " removed");
    }
}
