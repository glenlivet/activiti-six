package org.glenlivet.activiti.engine.impl.extens.agenda;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.impl.agenda.TakeOutgoingSequenceFlowsOperation;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.util.ProcessDefinitionUtil;
import org.apache.commons.lang3.StringUtils;

public class JumpableTakeOutgoingSequenceFlowsOperation extends TakeOutgoingSequenceFlowsOperation {

    public JumpableTakeOutgoingSequenceFlowsOperation(CommandContext commandContext, ExecutionEntity executionEntity, boolean evaluateConditions) {
        super(commandContext, executionEntity, evaluateConditions);
    }

    @Override
    protected void leaveFlowNode(FlowNode flowNode) {
        String jumpTo = execution.getVariableLocal("_jump_to", String.class);

        if (StringUtils.isNotEmpty(jumpTo)) {
            SequenceFlow outgoing = new SequenceFlow();
            BpmnModel bpmnModel = ProcessDefinitionUtil.getBpmnModel(this.execution.getProcessDefinitionId());
            FlowElement currentFlowElement = execution.getCurrentFlowElement();
            FlowElement targetFlowElement = bpmnModel.getFlowElement(jumpTo);
            outgoing.setSourceFlowElement(currentFlowElement);
            outgoing.setTargetFlowElement(targetFlowElement);

            execution.setCurrentFlowElement(outgoing);
            execution.setActive(true);
            Context.getAgenda().planContinueProcessOperation(execution);

            execution.removeVariableLocal("_jump_to");

        } else {
            super.leaveFlowNode(flowNode);
        }
    }
}
