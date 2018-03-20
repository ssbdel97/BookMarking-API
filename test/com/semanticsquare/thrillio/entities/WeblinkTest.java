package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.semanticsquare.thrillio.managers.BookmarkManager;

public class WeblinkTest {

	@Test
	public void testIsKidFriendlyEligible() {
		//Test1
		Weblink weblink = BookmarkManager.getInstance().createWebLink(2000,	"Taming Tiger, Part 2"	,"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",	"http://www.javaworld.com");
		boolean isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("for porn in url the isKidFriendlyEligible should return false",isKidFriendlyEligible);
		
		//Test2
		weblink = BookmarkManager.getInstance().createWebLink(2000,	"Taming porn, Part 2"	,"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("for porn in title the isKidFriendlyEligible should return false",isKidFriendlyEligible);
		
		//Test3
		weblink = BookmarkManager.getInstance().createWebLink(2000,	"Taming Tiger, Part 2"	,"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.adult.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("for adult in host the isKidFriendlyEligible should return false",isKidFriendlyEligible);
		
		//Test4
		weblink = BookmarkManager.getInstance().createWebLink(2000,	"Taming Tiger, Part 2"	,"http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html",	"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertTrue("for adult in url but not in host the isKidFriendlyEligible should return true",isKidFriendlyEligible);
		
		//Test5
		weblink = BookmarkManager.getInstance().createWebLink(2000,	"Taming adult, Part 2"	,"http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html",	"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertTrue("for adult in title but not in host the isKidFriendlyEligible should return true",isKidFriendlyEligible);
	}

}
