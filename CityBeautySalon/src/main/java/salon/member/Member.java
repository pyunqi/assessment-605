package salon.member;

public class Member {

	private String memberName;
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Member(String memberName) {
		this.memberName = memberName;
	}
	
}
