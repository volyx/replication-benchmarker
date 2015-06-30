
package jbenchmarker.factories;

import jbenchmarker.core.MergeAlgorithm;
import jbenchmarker.core.ReplicaFactory;
import jbenchmarker.logoot.BoundaryListStrategy;
import jbenchmarker.logoot.LogootDocument;
import jbenchmarker.logoot.LogootMerge;
import jbenchmarker.rgasplit.*;


public class RgaSFactory extends ReplicaFactory {

	@Override
	public MergeAlgorithm create(int r) {
		return new RgaSMerge(new RgaSDocument(), r);

	}

	static RgaSDocument createDoc(int r, int base) {
		return new RgaSDocument();
	}

	public static class ShortList<T> extends ReplicaFactory {
		@Override
		public RgaSMerge create(int r) {         
			return new RgaSMerge(createDoc(r, 16), r);
		}
	}
}

