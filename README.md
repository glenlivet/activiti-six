# activiti-six
An example to achieve free jumps with Activiti 6 by building a temporary outgoing sequence flow to the targeted node.

The key classes to help achieve the goal include:
* org/glenlivet/activiti/engine/impl/extens/agenda/JumpableActivitiEngineAgenda:
/br This overrides the default planTakeOutgoingSequenceFlowsOperation method with planning a {@JumpableTakeOutgoingSequenceFlowsOperation}.
* org/glenlivet/activiti/engine/impl/extens/agenda/JumpableTakeOutgoingSequenceFlowsOperation:
/br This is an extension of the default TakeOutgoingSequenceFlowsOperation class. It will check if there is a '_jump_to' variable in the current execution context, and will build a temporary path to the '_jump_to' node if so. The '_jump_to' shall be a valid BPMN node ID. 
    
An example has been given in this project to show how the extended Agenda can be utilized in a spring boot project.
Basically, a new ActivityEngineAgendaFactory shall be created and injected to the {@ProcessEngineConfigurationConfigurer} of which Spring boot is aware. 

_**ATTENTION**_

The business correctness of the node jump is out of the scope of this technical solution, and shall be considered case by case.
