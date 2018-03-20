package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.managers.BookmarkManager;

public class MovieTest {

	@Test
	public void testIsKidFriendlyEligible() {
		Movie movie = BookmarkManager.getInstance().createMovie(3000,	"Citizen Kane",	"", 1941, new String[]{"Orson Welles","Joseph Cotten"}, new String[] {"Orson Welles"}, MovieGenre.HORROR	, 8.5);
		boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();
		assertFalse("for Genre Horror isKidFriendlyEligible should be false",isKidFriendlyEligible);
		
		movie = BookmarkManager.getInstance().createMovie(3000,	"Citizen Kane",	"", 1941, new String[]{"Orson Welles","Joseph Cotten"}, new String[] {"Orson Welles"}, MovieGenre.THRILLERS	, 8.5);
		isKidFriendlyEligible = movie.isKidFriendlyEligible();
		assertFalse("for Genre thriller isKidFriendlyEligible should be false",isKidFriendlyEligible);
		
	}

}
