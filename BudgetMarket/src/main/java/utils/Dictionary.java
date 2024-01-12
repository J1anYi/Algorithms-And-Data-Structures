package utils;

public class Dictionary {
	private class DictionaryPair implements Comparable {
		private Object key;
		private Object value;

		public DictionaryPair(Object key, Object value) {
			this.key = key;
			this.value = value;
		}

		public Object getKey() {
			return key;
		}

		public void setKey(Object key) {
			this.key = key;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public int compareTo(Object o) {
			DictionaryPair other = (DictionaryPair) o;
			return ((Comparable) this.key).compareTo(other.key);
		}
	}

	private Vector<DictionaryPair> data;

	public Dictionary() {
		data = new Vector<>();
	}

	public void add(Object key, Object value) {
		int position = findPosition(key);
		if (position >= 0) {
			data.get(position).setValue(value);
		} else {
			data.add(new DictionaryPair(key, value));
		}
	}

	public int findPosition(Object key) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}

	public Object find(Object key) {
		int position = findPosition(key);
		if (position >= 0) {
			return data.get(position).getValue();
		}
		return null;
	}

	public void removeKey(Object key) {
		int position = findPosition(key);
		if (position >= 0) {
			data.remove(position);
		}
	}

	public int size() {
		return data.size();
	}
}
