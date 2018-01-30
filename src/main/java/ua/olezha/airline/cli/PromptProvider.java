package ua.olezha.airline.cli;

import org.jline.utils.AttributedString;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PromptProvider implements org.springframework.shell.jline.PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return AttributedString.fromAnsi("airline>");
    }
}
