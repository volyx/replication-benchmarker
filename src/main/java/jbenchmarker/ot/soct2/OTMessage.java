/**
 * Replication Benchmarker
 * https://github.com/score-team/replication-benchmarker/
 * Copyright (C) 2013 LORIA / Inria / SCORE Team
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
package jbenchmarker.ot.soct2;

import collect.VectorClock;
import crdt.Operation;
import java.io.Serializable;
import crdt.Operation;

/**
 * This Object is sent to another replicas.
 * Message contains remote operation with vector clock.
 * @param <Op> type of operation managed 
 * @author Stephane Martin 
 */
public class OTMessage<Op extends Operation> implements Operation, Serializable {
    

    protected VectorClock vc;
    protected int siteID;
    protected Op operation;

    /**
     * Construct of this object with followed elements 
     * @param vc Vector clock of the operation 
     * @param siteID Site id of sender
     * @param operation the operation 
     */
    public OTMessage(VectorClock vc, int siteID, Op operation) {
        //super(op);
        this.vc = vc;
        this.siteID = siteID;
        this.operation = operation;
    }

    /**
     * 
     * @return the operation
     */
    public Op getOperation() {
        return operation;
    }

    /**
     * Change the operation of message
     * @param operation
     */
    public void setOperation(Op operation) {
        this.operation = operation;
    }

    /**
     * 
     * @return site id of sender
     */
    public int getSiteId() {
        return siteID;
    }

    /**
     * change site id
     * @param siteID
     */
    public void setSiteId(int siteID) {
        this.siteID = siteID;
    }


    /**
     * @return the vector clock of the operation
     */
    public VectorClock getClock() {
        return vc;
    }

    /**
     * Change vector clock of messages
     * @param vc Vector Clock
     */
    public void setVc(VectorClock vc) {
        this.vc = vc;
    }
    
    /**
     * Clone the message with operation
     * @return new operation
     */
    @Override
    public OTMessage clone(){
        return new OTMessage(new VectorClock(vc),siteID,operation.clone() );
    }
    
    /**
     * String representation of SOCT2Message
     * @return
     */
    @Override
    public String toString(){
       return "SOCT2Message ("+ operation +", from:"+siteID+" vc:"+vc.toString()+" )";
    }

   

   

    
}
