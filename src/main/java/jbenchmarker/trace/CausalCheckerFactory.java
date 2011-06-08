/**
 * Replication Benchmarker
 * https://github.com/score-team/replication-benchmarker/
 * Copyright (C) 2011 INRIA / LORIA / SCORE Team
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
package jbenchmarker.trace;

import java.util.ArrayList;
import java.util.List;
import jbenchmarker.core.Document;
import jbenchmarker.core.MergeAlgorithm;
import jbenchmarker.core.Operation;
import jbenchmarker.core.ReplicaFactory;
import jbenchmarker.core.VectorClock;

/**
 * Check that operation are received in causal order.
 * @author urso
 */
public class CausalCheckerFactory implements ReplicaFactory {
//   @Override

    public MergeAlgorithm createReplica(int r) {
        return new MergeAlgorithm(new Document() {
            @Override
            public String view() {
                return "";
            }
            @Override
            public void apply(Operation op) {
            }
        }, r) {

            private VectorClock vc = new VectorClock();

            @Override
            protected void integrateLocal(Operation op) throws IncorrectTrace {
                check(op.getOriginalOp());
                this.getDoc().apply(op);
                vc.inc(op.getOriginalOp().getReplica());
            }

            @Override
            protected List<Operation> generateLocal(TraceOperation opt) throws IncorrectTrace {
                check(opt);
                List<Operation> l = new ArrayList<Operation>(1);
                Operation op = new Operation(opt) {

                    public Operation clone() {
                        return this;
                    }
                };
                l.add(op);
                this.getDoc().apply(op);
                vc.inc(this.getReplicaNb());
                return l;
            }

            private void check(TraceOperation op) throws IncorrectTrace {
                if (!vc.readyFor(op.getReplica(), op.getVC())) {
                    throw new IncorrectTrace("Replica " + this.getReplicaNb() + " not ready for " + op);
                }
            }
        };
    }
}