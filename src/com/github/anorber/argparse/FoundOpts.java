package com.github.anorber.argparse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class FoundOpts<E> {

	private final List<Option<E>> optList;
	private final Map<E, List<String>> optMap;

	FoundOpts() {
		optList = new ArrayList<Option<E>>();
		optMap = new HashMap<E, List<String>>();
	}

	FoundOpts(final FoundOpts<E> other) {
		optList = new ArrayList<Option<E>>(other.optList);
		optMap = new HashMap<E, List<String>>(other.optMap);
	}

	void addOption(final E id, final String argumentString) {
		optList.add(new Option<E>(id, argumentString));
		if (!optMap.containsKey(id)) {
			optMap.put(id, new ArrayList<String>());
		}
		optMap.get(id).add(argumentString);
	}

	boolean containsOption(final E option) {
		return optMap.containsKey(option);
	}

	List<String> getArgs(final E option) {
		return optMap.get(option);
	}

	Iterator<Option<E>> getIterator() {
		return optList.iterator();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return optList.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		return obj instanceof FoundOpts && optList.equals(((FoundOpts<?>) obj).optList);
	}
}
