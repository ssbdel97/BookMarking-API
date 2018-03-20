package com.semanticsquare.thrillio.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.dao.BookmarkDao;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.Movie;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.Weblink;
import com.semanticsquare.thrillio.util.HttpConnect;
import com.semanticsquare.thrillio.util.IOUtil;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	};

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Weblink createWebLink(long id, String title, String url, String host) {
		Weblink webLink = new Weblink();
		webLink.setHost(host);
		webLink.setId(id);
		webLink.setTitle(title);
		webLink.setUrl(url);
		return webLink;

	}

	public Book createBook(long id, String title, int publicationYear,
			String publisher, String[] authors, BookGenre genre,
			double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setGenre(genre);
		book.setAuthors(authors);
		book.setAmazonRating(amazonRating);

		return book;

	}

	public Movie createMovie(long id, String title, String profileUrl,
			int releaseYear, String[] cast, String[] directors, MovieGenre genre,
			double imdbRating) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setGenre(genre);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setImdbRating(imdbRating);
		return movie;
	}

	public List<List<Bookmark>> getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		// TODO Auto-generated method stub
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
		if (bookmark instanceof Weblink) {
			try {				
				String url = ((Weblink)bookmark).getUrl();
				if (!url.endsWith(".pdf")) {
					String webpage = HttpConnect.download(((Weblink)bookmark).getUrl());
					if (webpage != null) {
						IOUtil.write(webpage, bookmark.getId());
					}
				}				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dao.saveUserBookmark(userBookmark);
	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus,
			Bookmark b) {
		b.setKidFriendlyStatus(kidFriendlyStatus);
		b.setKidFriendlyMarkedBy(user);
		System.out.println("Kid friendly status is --- " + kidFriendlyStatus
				+ ", Marked By " + user + ", " + b);

	}

	public void share(User user, Bookmark b) {
		b.setSharedBy(user);
		System.out.println("Data to be shared ");
		if (b instanceof Book){
			System.out.println(((Book)b).getItemData());
		} else if (b instanceof Weblink){
			System.out.println(((Weblink)b).getItemData());
		}
		// TODO Auto-generated method stub
		
	}
}
