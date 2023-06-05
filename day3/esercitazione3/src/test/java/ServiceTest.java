import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.academy.cic.Dao;
import com.academy.cic.Service;
import com.academy.cic.entity.Course;
import com.academy.cic.entity.Module;


public class ServiceTest {
	
	 private Service service;
	    private Dao dao;
	    
	    @BeforeEach
	    public void setup() {
	        dao = mock(Dao.class);
	        service = new Service(dao);
	    }
	    
	    @Test
	    public void testAddModule() throws Exception {
	        int courseId = 1;
	        Course course = new Course();
	        course.setId(courseId);
	        
	        when(dao.findCourse(courseId)).thenReturn(course);
	        
	        List<Module> modules = new ArrayList<Module>();
	        modules.add(new Module());
	        modules.add(new Module());
	        when(dao.findModulesByCourseId(courseId)).thenReturn(modules);
	        
	        Module module = new Module();
	        int moduleCount = service.addModule(module, courseId);
	        
	        assertEquals(2, moduleCount);
	    }
	    
	    @Test
	    public void testAddModule_courseNotFound() throws Exception {
	        int courseId = 1;
	        
	        when(dao.findCourse(courseId)).thenReturn(null);
	        
	        
	        assertThrows(EntityNotFoundException.class, () -> {
	            service.addModule(new Module(), courseId);
	        });
	    }

}
