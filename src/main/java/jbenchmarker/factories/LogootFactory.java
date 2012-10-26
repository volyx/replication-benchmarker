/**
 * Replication Benchmarker
 * https://github.com/score-team/replication-benchmarker/
 * Copyright (C) 2012 LORIA / Inria / SCORE Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jbenchmarker.factories;

import jbenchmarker.core.ReplicaFactory;
import jbenchmarker.logoot.BoundaryStrategy;
import jbenchmarker.logoot.Component;
import jbenchmarker.logoot.LogootDocument;
import jbenchmarker.logoot.LogootIdentifier;
import jbenchmarker.logoot.LogootMerge;

/**
 *
 * @author urso
 */
public class LogootFactory<T> extends ReplicaFactory {
    @Override
    public LogootMerge<T> create(int r) {         
        return new LogootMerge<T>(createDoc(r, 64, 1000000000), r);
    }
    
    static public LogootIdentifier begin() {
        return new LogootIdentifier(new Component(0, -1, -1));
    }
    
    static public LogootIdentifier end(int base) {
        long max = (base == 64) ? Long.MAX_VALUE : (long) Math.pow(2, base) - 1;
        return new  LogootIdentifier(new Component(max, -1, -1));
    }
    
    static public LogootDocument createDoc(int r, int base, int bound) {
        return new LogootDocument(r, base, new BoundaryStrategy(bound), begin(), end(base)); 
    }
}
