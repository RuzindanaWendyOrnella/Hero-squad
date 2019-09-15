import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostTest {

    @Before
    public void setUp() throws Exception {
    }


    @After
    public void tearDown() throws Exception {
        Post.clearAllPosts(); //clear out all the posts before each test.

    }


    @Test
    public void getPublished_isFalseAfterInstantiation_false() throws Exception {
        Post myPost = new Post("Day 1: Intro");
        assertEquals(false, myPost.getPublished()); //should never start as published
    }
    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception{
        Post myPost = setupNewPost(); //see below
        assertEquals(LocalDateTime.now().getDayOfWeek(), myPost.getCreatedAt().getDayOfWeek());
    }

    public Post setupNewPost() {
        return new Post("Day 1: Intro");
    }
        @Test
        public void getId_postsInstantiateWithAnID_1() throws Exception{
            Post.clearAllPosts();  // Remember, the test will fail without this line! We need to empty leftover Posts from previous tests!
            Post myPost = new Post("Day 1: Intro");
            assertEquals(1, myPost.getId());
        }
    @Test
    public void findReturnsCorrectPost() throws Exception {
        Post.clearAllPosts();
        Post post = setupNewPost();
        assertEquals(1, Post.findById(post.getId()).getId());
    }

    @Test
    public void findReturnsCorrectPostWhenMoreThanOnePostExists() throws Exception {
        Post.clearAllPosts();
        Post post = setupNewPost();
        Post otherPost = new Post("How to pair successfully");
        assertEquals(2, Post.findById(otherPost.getId()).getId());
    }
        @Test
        public void updateChangesPostContent() throws Exception {
            Post post = setupNewPost();
            String formerContent = post.getContent();
            LocalDateTime formerDate = post.getCreatedAt();
            int formerId = post.getId();

            post.update("Android: Day 40");

            assertEquals(formerId, post.getId());
            assertEquals(formerDate, post.getCreatedAt());
            assertNotEquals(formerContent, post.getContent());
        }
            @Test
            public void deleteDeletesASpecificPost() throws Exception {
                Post post = setupNewPost();
                Post otherPost = new Post("How to pair successfully");
                post.deletePost();
                assertEquals(1, Post.getAll().size()); //one is left
                assertEquals(Post.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2. Why do we care?
            }
    @Test
    public void deleteAllPostsDeletesAllPosts() throws Exception {
        Post post = setupNewPost();
        Post otherPost = setupNewPost();

        Post.clearAllPosts();
        assertEquals(0, Post.getAll().size());
    }
        }




