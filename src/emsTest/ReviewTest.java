package emsTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import ems.BackEnd;
import ems.Member;
import ems.Review;
import ems.User;

public class ReviewTest {
	BackEnd backEnd = BackEnd.getInstance();
	Member reviewer;
	
	ArrayList<User> backEndUserList;
	
	double rating;
	
	String comment;
	
	@Test 
	public void testGetMemberWithNoMemberInBackEnd() {
		backEndUserList = backEnd.getUsers();
		this.backEndUserList.clear();
		reviewer = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		rating = 98;
		comment = "Good";
		Review tReview = new Review(this.reviewer, this.rating, this.comment);
		Member tResult = tReview.getMember();
		assertEquals(null, tResult);
		
		
	}
	
	@Test 
	public void testGetMemberWithTheSameUserInBackEnd() {
		backEndUserList = backEnd.getUsers();
		this.backEndUserList.clear();
		reviewer = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		this.backEndUserList.add(reviewer);
		rating = 98;
		comment = "Good";
		Review tReview = new Review(this.reviewer, this.rating, this.comment);
		Member tResult = tReview.getMember();
		assertEquals(reviewer, tResult);
		backEndUserList.clear();
	}
	
	@Test
	public void testGetMemberWithTwoUsersInBackEnd() {
		backEndUserList = backEnd.getUsers();
		this.backEndUserList.clear();
		Member member2 = new Member("userLoginId2", "userPassword2", "userName2", 20, "A245678");
		reviewer = new Member("userLoginId", "userPassword", "userName", 20, "A1234567");
		this.backEndUserList.add(member2);
		this.backEndUserList.add(reviewer);
		rating = 98;
		comment = "Good";
		Review tReview = new Review(this.reviewer, this.rating, this.comment);
		Member tResult = tReview.getMember();
		assertEquals(reviewer, tResult);
		backEndUserList.clear();
	}
	
	

}
