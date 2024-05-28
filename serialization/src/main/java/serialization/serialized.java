package serialization;

import java.io.*;

class ddd implements Serializable{}
class b extends ddd {}
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
	//final a obj;
	final b objb=new b();
	int a[];
	String kk[];	
	FileOutputStream fos;
		
	String ref = new String("reference variable of string");
	//public emp(String name,String job,int salary,float f,char c,double d,short s,boolean bb,byte b,int a[],String kk[],FileOutputStream fos,a obj){
	
	public emp(String name,String job,int salary,float f,char c,double d,short s,boolean bb,byte b,int a[],String kk[],File file){	
	
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
		this.fos=fos;
		//this.obj=obj;
	}
	public void dis(){
		System.out.println("name ==>"+name+" job==>"+job+" salary==>"+salary);
		System.out.println("flag ==>"+flag+" ch ==>"+ch+" ll==>"+ll);
		System.out.println("dd ==>"+dd+" s ==>"+s+" b ==>"+b+" int array is "+a);
		System.out.println("fos "+fos);
		//System.out.println("a is "+obj);
		System.out.println("b is  "+objb);
		
	}
	public emp(){
		System.out.println("default constructor");
	}
		
}

class a implements Serializable{
	
	String gur;
	public a(String name){
		gur=name;
	}
	public void dis(){
		
		System.out.println("gur ==>"+gur);
	}
	public String toString(){
		return gur;
	}
		
}

class serialized{
	
	public static void main(String args[]){
	
			int array[]={12,34,56};
			String aa[]={"ty","56"};
			short ss=9;byte bb=67;
		try{
			emp e1 = new emp("money","java-developer",50000,78.89f,'J',90.09,ss,true,bb,array,aa,new File("file.txt"));	
			
			System.out.println("serializing objects.......");
			e1.dis();
			System.out.println(e1);
		
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("emp.obj"));
			out.writeObject(e1);
			System.out.println("serialized done successfully\n");	
		}catch(Exception e){
			System.out.println(e);
		}	
		try{
			
			Thread.sleep(10000);
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("emp.obj"));
			Object des = in.readObject();
			System.out.println(des);
			emp desemp=(emp)des;
			System.out.println(desemp);
			desemp.dis();
			//System.out.println("desemp.obj final is "+desemp.obj);
			//desemp.obj.dis(); 
			System.out.println("desemp.obj final is "+desemp.objb);
			System.out.println("De-serialized done successfully\n");	
		}catch(Exception e){
			System.out.println(e);
		}
		
	}	
}