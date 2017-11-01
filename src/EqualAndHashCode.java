import java.util.*;

/*
 * equal() and hashCode() matters when using HashMap but how about in a set.
 */
class Student {
  String name;

  Student(String name) {
    this.name = name;
  }

  Student(String name, String id) {
    this.name = name;
  }

  public String toString() {
    return this.name;
  }

//  @Override
//  public boolean equals(Object o) {
//    System.out.println("Student's equals() was called");
//    if(this==o) return true;
//    if(o==null) return false;
//    if(getClass()!=o.getClass()) return false;
//    
//    Student s=(Student) o;
//    return Objects.equals(this.name, s.name);
//  }

  public int hashCode() {
    System.out.println("Student's hashCode() was called: "+this.name.hashCode());
    return this.name.hashCode();
  }
}

class EqualAndHashCode {

  public static void main(String[] args) {
    Map<Student, Integer> map = new HashMap<Student, Integer>();
    Student s = new Student("Bin");
    map.put(s, 3);
    map.put(s, 3);
    map.put(new Student("Bin"), 3);
    map.put(new Student("Bin"), 3);
    map.put(new Student("Bin"), 2);
    map.put(new Student("Bin"), 2);

    System.out.println(map);

  }
}
