package assignment;

import java.util.Arrays;

public class Assignment4 {
    // question 1: split license key into k groups
    public String splitLicenseKey(String key, int k){
        StringBuffer splittedKey=new StringBuffer();
        char[] chs = key.toCharArray();
        int counter=0;
        for(int i=chs.length-1;i>=0;i--){
            if(chs[i]!='-'){
                splittedKey.append(Character.toUpperCase(chs[i]));
                counter++;
            }
            if(counter%k==0 && i!=0) splittedKey.append('-'); 
        }
        return splittedKey.reverse().toString();
    }
    
    // question 5
    public String intToRoman(int num) {
        final int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        final String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        
        StringBuffer roman = new StringBuffer();
        int i = 0;
        while(num>0){
            int k = num / values[i];
            while(k-->0){
                roman.append(symbols[i]);
                num -= values[i];
            }
            i++;
        }
        return roman.toString();
    }
    
    public static void main(String[] args) {
        Assignment4 test=new Assignment4();
        // test question 1
        String input = "2-4A0r7-4k";
        System.out.println(test.splitLicenseKey(input, 4));
        System.out.println(test.splitLicenseKey(input, 3));
        
        // test question 2
        RockPaperScissorsGame.main(args);

        // test question 3
        IpAddressDriver.main(args);
        
        // test question 4
        CourseRegisterDrive.main(args);
        
        // test question 5
        System.out.println(new Assignment4().intToRoman(3999));
    }
}

// question 2
class Tool{
    private int strength;
    protected char type;
    
    Tool(){}
    Tool(int s){this.strength=s;}
    
    public void setStrength(int n){
        this.strength=n;
    }
    
    public int getStrength(){
        return strength;
    }    
}

class Scissors extends Tool{
    Scissors(){}
    Scissors(int s){
        super.setStrength(s);
        super.type='s';
    }
    
    public boolean fight(Tool tool){
        double factor=1.0;
        if(tool.getClass()==Paper.class) factor*=2;
        if(tool.getClass()==Rock.class) factor/=2;
        
        // put factor first to do a 'double' compare
        return factor*this.getStrength() > tool.getStrength();
    }
}

class Paper extends Tool{
    Paper(){}
    Paper(int s){
        super.setStrength(s);
        super.type='p';
    }
    
    public boolean fight(Tool tool){
        double factor=1.0;
        if(tool.getClass()==Rock.class) factor*=2;
        if(tool.getClass()==Scissors.class) factor/=2;
        
        // put factor first to do a 'double' compare
        return factor*this.getStrength() > tool.getStrength();
    }
}

class Rock extends Tool{
    Rock(){}
    Rock(int n){
        super(n);
        super.type='r';
    }
    
    public boolean fight(Tool tool){
        double factor=1.0;
        if(tool.getClass()==Scissors.class) factor*=2;
        if(tool.getClass()==Paper.class) factor/=2;
        
        // put factor first to do a 'double' compare
        return factor*this.getStrength() > tool.getStrength();
    }
}

class RockPaperScissorsGame {
    public static void main(String args[]) {
        Scissors s = new Scissors(5);
        Paper p = new Paper(7);
        Scissors r = new Scissors(15);

        System.out.println(s.fight(p) + " , " + p.fight(s));
        System.out.println(p.fight(r) + " , " + r.fight(p));
        System.out.println(r.fight(s) + " , " + s.fight(r));
    }
}
// question 3
class IpAddress{
    private String dottedDecimal;
    private int firstOctet, secondOctet, thirdOctet, fourthOctet;
    
    public void setDottedDecimal(String IP){
        this.dottedDecimal=IP;
    }
    public String getDottedDecimal(){
        return this.dottedDecimal;
    }
    
    IpAddress(){};
    IpAddress(String IP){
        this.dottedDecimal=IP;
        this.setOctets(this.splitIP());
    }
    
    private void setOctets(int[] octets){
        assert octets.length==4;
        firstOctet=octets[0];
        secondOctet=octets[1];
        thirdOctet=octets[2];
        fourthOctet=octets[3];
    }
    
    // could be easy to use string's split function    
    private int[] splitIP(){
        final int N=4;
        int ip[]=new int[N];
        StringBuilder sb=new StringBuilder();
        for(int i=0, j=0;i<N&&j<this.dottedDecimal.length();j++){
            while(j<dottedDecimal.length() && dottedDecimal.charAt(j)!='.'){
                sb.append(dottedDecimal.charAt(j));
                j++;
            }
            ip[i++]=Integer.parseInt(sb.toString());
            
            sb=new StringBuilder();
        }
        return ip;
    }
    
    public int getOctet(int p){
        assert p<=1 && p<=4;
        if(p==1) return firstOctet;
        else if(p==2) return secondOctet;
        else if(p==3) return thirdOctet;
        else if(p==4) return fourthOctet;
        else throw new IllegalArgumentException();
    }
}

class IpAddressDriver{
    public static void main(String args[]){
        IpAddress ip = new IpAddress("216.27.6.136");
        System.out.println(ip.getDottedDecimal());
        System.out.println(ip.getOctet(4));
        System.out.println(ip.getOctet(1));
        System.out.println(ip.getOctet(3));
        System.out.println(ip.getOctet(2));
    }
}

// question 4
class Student{
    private String name;
    private int id;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
//    Student(){}
    Student(String n, int i){
        this.name=n;
        this.id=i;
    }
    
    public String toString(){
        return id+", "+name;
    }
}

class Course{
    private String name;
    private int numberOfStudent=0;
    private final int maxOfStudent=10;
    private Student[] students=new Student[maxOfStudent];
    
    public Course(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
    
    public boolean isFull(){
        return numberOfStudent==maxOfStudent;
    }
    
    public void registerStudent(Student student){
        if(!this.isFull()){
            students[numberOfStudent++]=student;
        }else{
            System.out.println("Cannot register \""+student+"\" because the class is full.");
        }
    }    
}
// test class register system. I change the name from 'Test' to 'CourseRegisterDrive'
class CourseRegisterDrive {
    public static void main(String[] args) {
        String[] names={"Bin","Tianyu","Yuhan","Lulu","Zihan","Aarabhi","Lulu","Yujia","Chun","Liuhui","Xiaoxiao"};
        int[] ids = {1822, 1823, 1824, 1825, 1826, 1827, 1828, 1829, 1830, 1831, 1832};

        Student[] stu5100 = new Student[names.length];

        for (int i = 0; i < names.length && i < ids.length; i++) {
            stu5100[i] = new Student(names[i], ids[i]);
        }

        Course info5100 = new Course("Application Engineering and Development");
        for (Student s : stu5100) {
            if (s != null)
                info5100.registerStudent(s);
        }

        Student[] registeredStudent = info5100.getStudents();
        System.out.println("Here is the registered studetns for course: " + info5100.getName());
        for (Student s : registeredStudent) {
            System.out.println(s);
        }
        System.out.println("The total registerred student number is: " + info5100.getNumberOfStudent());
    }
}

