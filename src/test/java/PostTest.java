
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PostTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws Exception {
        Post hero = new Post("Benjamin Carson", 40, "Doctor", "anger");
        assertEquals(true, hero instanceof Post);
    }

    @Test
    public void getPublished_isFalseAfterInstantiation_false() throws Exception {
        Post myHero = new Post("Day 1", 9, " Intro", "none");
        assertEquals(false, myHero.getPublished()); //should never start as published
    }

    @Test
    public void AllHeroObjectsCorrectlyReturned_true() {
        Post hero = new Post("Benjamin Carson", 40, "Doctor", "anger");
        Post otherHero = new Post("Barack Obama", 55, "Ex-President", "arrogance");
        assertEquals(true, Post.getAll().contains(hero));
        assertEquals(true, Post.getAll().contains(otherHero));
    }

    @Test
    public void getId_postsInstantiateWithAnID_1() throws Exception {
        Post.clearAllPosts();  // Remember, the test will fail without this line! We need to empty leftover Posts from previous tests!
        Post myHero = new Post("Daniel Brown", 72, "writer", "poor time management");
        assertEquals(1, myHero.getId());
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception {
        Post myHero = setupNewHero(); //see below
        assertEquals(LocalDateTime.now().getDayOfWeek(), myHero.getCreatedAt().getDayOfWeek());
    }

    public Post setupNewHero() {
        return new Post("wendy", 18, " Programmer", "laziness");
    }

    @Test
    public void findReturnsCorrectPost() throws Exception {
        Post.clearAllPosts();
        Post hero = setupNewHero();
        assertEquals(1, Post.findById(hero.getId()).getId());
    }

    @Test
    public void findReturnsCorrectPostWhenMoreThanOnePostExists() throws Exception {
        Post.clearAllPosts();
        Post hero = setupNewHero();
        Post otherHero = new Post("Paul Kagame", 60, "leadership", "none");
        assertEquals(2, Post.findById(otherHero.getId()).getId());
    }


    @Test
    public void updateChangesPostContent() throws Exception {
        Post hero = setupNewHero();
        ArrayList<Post> formerContent = hero.getAll();
        LocalDateTime formerDate = hero.getCreatedAt();
        int formerId = hero.getId();

        hero.update("Android",40,"leader","dictator");

        Assert.assertEquals(formerId, hero.getId());
        Assert.assertEquals(formerDate, hero.getCreatedAt());
        assertNotEquals(formerContent, hero.getAll());
    }

    @Test
    public void deleteDeletesASpecificPost() throws Exception {
        Post hero = setupNewHero();
        Post otherHero = new Post("How to pair successfully", 99, "ffff", "dddd");
        otherHero.deletePost();
        Assert.assertEquals(1, Post.getAll().size()); //one is left
        Assert.assertEquals(Post.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2. Why do we care?
    }

    @Test
    public void deleteAllPostsDeletesAllPosts() throws Exception {
        Post hero = setupNewHero();
        Post otherHero = setupNewHero();

        Post.clearAllPosts();
        Assert.assertEquals(0, Post.getAll().size());
    }
}
