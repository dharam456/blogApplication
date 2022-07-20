package mainpackage.controller;

import mainpackage.model.Post;
import mainpackage.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @PostMapping("/savepost")
    public String savePost(@RequestBody Post post){
        postRepository.save(post);
        return "post have been saved succesfully!";
    }
}
