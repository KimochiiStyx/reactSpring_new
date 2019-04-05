package fi.vamk.tka.reactspring.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories(basePackageClasses = GroupRepository.class)
public class GroupTest {
    @Autowired
    private GroupRepository repository;

    @Test
    public void justTesting() {
        System.out.println("-----------test,test,test-----------------");
    }

    @Test
    public void againTesting() {
        System.out.println("-----------test,test,test,test---------------");
    }

    @Test
    public void addGroupAndVerifyTest() {
        // create an instance
        Group item = new Group();
        item.setName("Vaasa JUG");
        repository.save(item);
        Group found = repository.findByName("Vaasa JUG");
        assertNotNull(found);
        if (found != null) {
            assertEquals(found.getName(), item.getName());
            repository.delete(found);
        }
    }
}