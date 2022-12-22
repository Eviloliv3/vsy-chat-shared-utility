package de.vsy.shared_utility.id_manipulation;

public class IdComparator {

  private IdComparator() {
  }

  /**
   * Returns the id that does not equal the first parameter
   *
   * @param clientId     the id to check against
   * @param originatorId the first id to check
   * @param recipientId  the second id to check
   * @return the first id that is not equal to the first argument; -1 else
   */
  public static int determineContactId(int clientId, int originatorId, int recipientId) {
    int contactId = -1;
    final var originatorIsContact = clientId != originatorId;
    final var recipientIsContact = clientId != recipientId;

    if (originatorIsContact) {
      contactId = originatorId;
    } else if (recipientIsContact) {
      contactId = recipientId;
    }
    return contactId;
  }
}
