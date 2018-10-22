package First.BotLogic;

import First.TypoCorrect.TypoCorrecter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class User {
	
	private long id;
	private List<MessageListener> listeners = new ArrayList<>();
	private List<String> receivedFromBotMessages = new ArrayList<>();
	private HashSet<Integer> hashesReceivedJokes = new HashSet<>();
	private TypoCorrecter correcter;
	private boolean isPlaying = false;


	public void learnJoke(String joke){
		hashesReceivedJokes.add(joke.hashCode());
	}

	public HashSet<Integer> getHashesReceivedJokes(){
		return hashesReceivedJokes;
	}

	public boolean getIsPlaying(){
		return isPlaying;
	}

	public void changeIsPlaying(){
		isPlaying = !isPlaying;
	}

	public User(long id, TypoCorrecter correcter) {
		this.correcter = correcter;
		this.id = id;
	}
	
	public List<String> getReceivedFromBotMessages() {
		return receivedFromBotMessages;
	}
	
	public void addListener(MessageListener listenerToAdd) {
		if (!listeners.contains(listenerToAdd)) {
			listeners.add(listenerToAdd);
		}
	}
	
	public void removeListener(MessageListener listenerToRemove) {
		listeners.remove(listenerToRemove);
	}
	
	public void sendMessage(String message) {
		receivedFromBotMessages.clear();
		var listenersCount = listeners.size();
		for (var numberOfListener = 0; numberOfListener < listenersCount; numberOfListener++) {
			var messageFromBot = listeners.get(numberOfListener).onMessage(message, this, correcter);
			if (messageFromBot != null) {
				receivedFromBotMessages.add(messageFromBot);
			}
		}
	}
}