package org.jenkinsci.plugins.buildstep;

import org.jenkinsci.plugins.buildstep.ManageSlaveBuildStep.ManageSlaveBuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.DescriptorExtensionList;
import hudson.Extension;
import hudson.Launcher;
import hudson.init.InitMilestone;
import hudson.init.Initializer;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Items;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;

public class ManageSlaveBuildStepContainer extends Builder{
	private final ManageSlaveBuildStep buildStep;
	
	@DataBoundConstructor
	public ManageSlaveBuildStepContainer(final ManageSlaveBuildStep buildStep) {
		this.buildStep = buildStep;
	}
	
	public ManageSlaveBuildStep getBuildStep() {
		return buildStep;
	}
	
	@Override
	public boolean perform(final AbstractBuild<?, ?> build, final Launcher launcher, final BuildListener listener)  {
		try {
			listener.getLogger().println("starting ------");
			return buildStep.perform(build, launcher, listener);
		} catch (Exception e) {
			listener.getLogger().println(e.getMessage());
		}
		return false;	
	}
	
	@Extension
	public static final class ManageSlaveBuildStepContainerDescriptor extends BuildStepDescriptor<Builder> {

		@Initializer(before=InitMilestone.PLUGINS_STARTED)
		public static void addAliases() {
			Items.XSTREAM2.addCompatibilityAlias(
					"org.jenkinsci.plugins.monitorslave.VSphereBuildStepContainer",
					ManageSlaveBuildStepContainer.class
					);
		}

		@Override
		public String getDisplayName() {
			return "manage-slave build step";
		}

		public DescriptorExtensionList<ManageSlaveBuildStep, ManageSlaveBuildStepDescriptor> getBuildSteps() {
			return ManageSlaveBuildStep.all();
		}

		@Override
		public boolean isApplicable(Class<? extends AbstractProject> jobType) {
			return true;
		}
	}
}