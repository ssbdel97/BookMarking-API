package com.semanticsquare.thrillio.controllers;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookmarkManager;

public class BookmarkController {
	private static BookmarkController instance = new BookmarkController(); 
	private BookmarkController(){}
	public static BookmarkController getInstance(){
		return instance;
	}
	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
		// TODO Auto-generated method stub
		
	}
	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark b) {
		BookmarkManager.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus,b);
	}
	public void share(User user, Bookmark b) {
		BookmarkManager.getInstance().share(user,b);
		// TODO Auto-generated method stub
		
	}
	
}
