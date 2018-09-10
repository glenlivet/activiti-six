package org.glenlivet.activiti.engine.impl.extens.agenda;

import org.activiti.engine.impl.agenda.DefaultActivitiEngineAgenda;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

public class JumpableActivitiEngineAgenda extends DefaultActivitiEngineAgenda {

    public JumpableActivitiEngineAgenda(CommandContext commandContext) {
        super(commandContext);
    }

    @Override
    public void planTakeOutgoingSequenceFlowsOperation(ExecutionEntity execution, boolean evaluateConditions) {
        planOperation(new JumpableTakeOutgoingSequenceFlowsOperation(commandContext, execution, evaluateConditions));
    }
}
