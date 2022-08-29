package de.vsy.chat.shared_utility.id_manipulation;

public
class IdComparator {

    private
    IdComparator () {}

    public static
    int determineContactId (int clientId, int originatorId, int recipientId) {
        return determineIfOriginator(clientId,
                                     originatorId) ? recipientId : originatorId;
    }

    public static
    boolean determineIfOriginator (int clientId, int originatorId) {
        return clientId == originatorId;
    }
}
