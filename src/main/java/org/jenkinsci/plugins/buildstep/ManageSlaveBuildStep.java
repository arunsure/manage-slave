package org.jenkinsci.plugins.buildstep;

import jenkins.model.Jenkins;
import hudson.DescriptorExtensionList;
import hudson.ExtensionPoint;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.Describable;
import hudson.model.Descriptor;

public abstract class ManageSlaveBuildStep implements Describable<ManageSlaveBuildStep>, ExtensionPoint {

	public Descriptor<ManageSlaveBuildStep> getDescriptor() {
		// TODO Auto-generated method stub
		return (ManageSlaveBuildStepDescriptor)Jenkins.getInstance().getDescriptor(getClass());
	}
	
	public static DescriptorExtensionList<ManageSlaveBuildStep, ManageSlaveBuildStepDescriptor> all() {
		return Jenkins.getInstance().getDescriptorList(ManageSlaveBuildStep.class);
	}
	
	public abstract boolean perform(final AbstractBuild<?, ?> build, final Launcher launcher, final BuildListener listener) throws Exception;
	
	public static abstract class ManageSlaveBuildStepDescriptor extends Descriptor<ManageSlaveBuildStep> {

		protected ManageSlaveBuildStepDescriptor() { }
		
		protected ManageSlaveBuildStepDescriptor(Class<? extends ManageSlaveBuildStep> clazz) {
			super(clazz);
		}
	}
}