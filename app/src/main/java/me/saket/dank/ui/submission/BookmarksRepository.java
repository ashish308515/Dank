package me.saket.dank.ui.submission;

import net.dean.jraw.models.Identifiable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import me.saket.dank.utils.RxHashSet;

@Singleton
public class BookmarksRepository {

  private final RxHashSet<String> savedFullNames = new RxHashSet<>();

  @Inject
  public BookmarksRepository() {
  }

  public void markAsSaved(Identifiable contribution) {
    // TODO: ignore SyntheticData.ID_SUBMISSION_FOR_GESTURE_WALKTHROUGH.
    savedFullNames.add(contribution.getFullName());
  }

  public void markAsUnsaved(Identifiable contribution) {
    savedFullNames.remove(contribution.getFullName());
  }

  public boolean isSaved(Identifiable contribution) {
    return savedFullNames.contains(contribution.getFullName());
  }

  public Observable<Object> streamChanges() {
    return savedFullNames.changes().cast(Object.class);
  }
}
