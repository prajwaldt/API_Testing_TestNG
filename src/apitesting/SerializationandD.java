package apitesting;

public class SerializationandD {

	private String name;
	private String place;

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getplace() {
		return place;
	}

	public void setplace(String place) {
		this.place = place;
	}

	public static void main(String[] args) {
		SerializationandD sd=new SerializationandD();
		sd.setname("fird");
		sd.setplace("bng");
		System.out.println(sd.getname());
		System.out.println(sd.getplace());
		
	}

}
