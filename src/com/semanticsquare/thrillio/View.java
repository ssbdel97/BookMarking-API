package com.semanticsquare.thrillio;

import java.util.List;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.partner.Shareable;

public class View {
	public static void browse(User user,  List<List<Bookmark>> bookmarks) {
		System.out.println("\n" + user.getEmail() + " is browsing items... \n");
		int bookmarkCount = 0;
		for ( List<Bookmark> b2 : bookmarks) {
			for (Bookmark b : b2) {
				//if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
					boolean isBookmark = getBookmarkDecision(b);
					if (isBookmark) {
						bookmarkCount++;
						BookmarkController.getInstance().saveUserBookmark(user,
								b);
						System.out.println("New item bookmarked--" + b);
					}
				//}
				if (user.getUserType().equals(UserType.EDITOR)
						|| user.getUserType().equals(UserType.CHIEF_EDITOR)) {
					if (b.isKidFriendlyEligible()
							&& b.getKidFriendlyStatus().equals(
									KidFriendlyStatus.UNKNOWN)) {
						KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision(b);
						if (!kidFriendlyStatus
								.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance()
									.setKidFriendlyStatus(user,
											kidFriendlyStatus, b);

						}
					}

					// Sharing!!
					if (b.getKidFriendlyStatus().equals(
							KidFriendlyStatus.APPROVED)
							&& b instanceof Shareable) {
						boolean isShared = getShareDecision();
						if(isShared){
							BookmarkController.getInstance().share(user,b);
						}
					}
				}
			}
		}
	}

	private static boolean getShareDecision() {
		// TODO Auto-generated method stub
		return Math.random() < 0.5 ? true : false;
	}



	private static KidFriendlyStatus getKidFriendlyStatusDecision(Bookmark b) {
		// TODO Auto-generated method stub
		return Math.random() < 0.4 ? KidFriendlyStatus.APPROVED
				: (Math.random() >= 0.5 && Math.random() < 0.8) ? KidFriendlyStatus.REJECTED
						: KidFriendlyStatus.UNKNOWN;
	}

	private static boolean getBookmarkDecision(Bookmark b) {
		// TODO Auto-generated method stub
		return Math.random() < 0.5 ? true : false;
	}
}
