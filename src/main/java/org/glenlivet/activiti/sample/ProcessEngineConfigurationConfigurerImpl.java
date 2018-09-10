package org.glenlivet.activiti.sample;

import org.activiti.engine.ActivitiEngineAgenda;
import org.activiti.engine.ActivitiEngineAgendaFactory;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.glenlivet.activiti.engine.impl.extens.agenda.JumpableActivitiEngineAgenda;

public class ProcessEngineConfigurationConfigurerImpl implements ProcessEngineConfigurationConfigurer {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setEngineAgendaFactory(new ActivitiEngineAgendaFactory() {
            @Override
            public ActivitiEngineAgenda createAgenda(CommandContext commandContext) {
                return new JumpableActivitiEngineAgenda(commandContext);
            }
        });
    }
}
