package com.semanticsquare.thrillio.dao;

import java.util.ArrayList;
import java.util.List;

import com.semanticsquare.thrillio.DataStore;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.Weblink;

public class BookmarkDao {

	public List<List<Bookmark>> getBookmarks(){
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		// TODO Auto-generated method stub
		DataStore.add(userBookmark);
		
	}
	public List<Weblink> getAllWebLinks(){
		List<Weblink> result = new ArrayList<>();
		List<List<Bookmark>> bookmarks = DataStore.getBookmarks();
		List<Bookmark> allWeblinks = bookmarks.get(0);
		for (Bookmark bookmark : allWeblinks) {
			result.add((Weblink)bookmark);
		}		
		return result;
	}
	public List<Weblink> getWebLinks(Weblink.DownloadStatus downloadStatus){
		List<Weblink> result = new ArrayList<>();
		List<Weblink> allWeblinks = getAllWebLinks();
		for (Weblink weblink : allWeblinks) {
			if(weblink.getDownloadStatus().equals(downloadStatus)) {
				result.add(weblink); 
			}
		}
		return result;
	}
}
