package me.lucyy.watercore.api.exception;

public class ModuleInitException extends RuntimeException {
	public ModuleInitException(String moduleName, Throwable cause) {
		super("while initialising module " + moduleName, cause);
	}
}
