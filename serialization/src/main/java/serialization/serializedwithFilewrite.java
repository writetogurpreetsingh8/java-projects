package serialization;
import java.io.*;

class ddd implements Serializable{}

class emp extends ddd 
{
	
	private String name,job;
	protected int salary;
	public boolean flag=true;
	char ch='G';
	 float ll=9.0f;
	double dd=90.09;
	short s=1;byte b=23;	
	int jj=90;
	File file;
	FileOutputStream fos;
	
	int a[];
	String kk[];
	int kkkk;	
	
	String ref = new String("reference variable of string");
	public emp(String name,String job,int salary,float f,char c,double d,short s,boolean bb,byte b,int a[],String kk[],File file,FileOutputStream fos){
	
	  //public emp(String name,String job,int salary,float f,char c,double d,short s,boolean bb,byte b,int a[],String kk[],File file){	
	
		this.name=name;
		this.job=job;
		this.salary=salary;
		ll=f;
		ch=c;
		dd=d;
		this.s=s;
		flag=bb;
		this.b=b;
		this.a=a;
		this.kk=kk;
		this.file=file;
		this.fos=fos;
		System.out.println("parameterized constructor of emp ");
		
	}
	public void dis(){
		System.out.println("name ==>"+name+" job==>"+job+" salary==>"+salary);
		System.out.println("flag ==>"+flag+" ch ==>"+ch+" ll==>"+ll);
		System.out.println("dd ==>"+dd+" s ==>"+s+" b ==>"+b+" int array is "+a+" kk is "+kk);
		System.out.println("file "+file);
		System.out.println("file out put stream "+fos);	
		
		
	}
	public emp(){
		System.out.println("default constructor of emp ");
	}
		
}


class serializedwithFilewrite{
	
	public static void main(String args[]){
	
			int array[]={12,34,56};
			String aa[]={"ty","56"};
			short ss=9;byte bb=67;
		try{
			FileOutputStream f = new FileOutputStream("run.txt");
		
			emp e1 = new emp("money","java-developer",50000,78.89f,'J',90.09,ss,true,bb,array,aa,new File("F:/Tech Mentro/serialization/file.txt"),f);	
			
			System.out.println("serializing objects.......");
			e1.dis();
			System.out.println(e1);
			
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("emp.obj"));
			
			out.writeObject(e1);
					
			FileOutputStream fos = new FileOutputStream(e1.file);
			fos.write("data from file out put stram /n and it will be store into file object /n and try to find out serailzed object into file ".getBytes());
			fos.flush();
			fos.close();
			
			System.out.println("serialized done successfully\n");	
		}catch(Exception e){
			System.out.println(e);
		}	
		
		
	}	
}