package students;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ModelFactory 
{
	INST;
	private ModelFactory() { }
	
	public static final String STUDENT_DATA_FILE = "StudentData.txt";
	public static final String COURCE_GRADE_DATA_FILE = "CourseGradeData.txt";
	
	private Map<Integer, Student> studentMap = new HashMap<>();
	
	/***
	 * Build a cached map of all students 
	 */
	public void initialize() 
	{
	   File f = new File(STUDENT_DATA_FILE);
	   try( Stream<String> studentStream = Files.lines(Paths.get(STUDENT_DATA_FILE)); 
	         Stream<String> courseGradeStream = Files.lines(Paths.get(COURCE_GRADE_DATA_FILE)) )
      {
	      studentMap.clear();
	      Pattern p = Pattern.compile("(.*),(.*)");
	      List<List<String>> studentList = studentStream.map(line -> processLine(line, p)).collect(Collectors.toList());
	      Pattern p2 = Pattern.compile("(.*),(.*),(.*),(.*)");
	      List<List<String>> courseGradeList = courseGradeStream.map(line -> processLine(line, p2)).collect(Collectors.toList());
	      
	      studentMap = studentList.stream()
	        .filter(l -> l.size() > 0)
	        .collect(Collectors.toMap(s -> Integer.parseInt(s.get(1)),  s -> createStudent(s, courseGradeList)));
	      //studentMap.forEach((k, v) -> System.out.println("K: "+k+", V: "+v));
	    }
	    catch(IOException ex) { ex.printStackTrace(); }
	}
	
	/***
	 * Get all students
	 * @return
	 */
	public Map<Integer, Student> getStudentMap()
	{
	   if(studentMap.isEmpty())
	      initialize();
	   return Collections.unmodifiableMap(studentMap);
	}
	
	/***
	 * Find a Student
	 * 
	 * @param id: The student id.
	 * @return
	 */
	public Student getStudent(int id) 
	{
	   return studentMap.get(id);
	}
	
	/***
	 * Take an input string and using the regx pattern, break the string up into a list of fields.
	 * 
	 * @param input: The string to be processed
	 * @param pattern: The reqx pattern to use to capture the list of output fields
	 * @return
	 */
	private List<String> processLine(String input, Pattern pattern )
	{
	   List<String> fields = new ArrayList<>();
	   Matcher m = pattern.matcher(input);
	   if(m.matches())
	   {
	      for(int i=0; i<m.groupCount(); i++)
	         fields.add(m.group(i+1));
	   }
	   return fields; 
	}
	
	/***
	 * Create a Student class
	 * 
	 * @param studentData: The list of student data fields
	 * @param courseGradeData: The list of course an grade data for that student
	 * @return
	 */
	private Student createStudent(List<String> studentData, List<List<String>> courseGradeData)
	{
	   // Build list of Course object for this student
	   List<Course> cList = courseGradeData.stream()
	         .filter(l -> l.size() > 0)
	         .filter(l -> l.get(0).equals(studentData.get(1)))
	         .map(l -> new Course(l.get(1), Boolean.parseBoolean(l.get(2)), Double.parseDouble(l.get(3))))
	         .collect(Collectors.toList());
	   return new Student(studentData.get(0), Integer.parseInt(studentData.get(1)), cList);
	}
}
