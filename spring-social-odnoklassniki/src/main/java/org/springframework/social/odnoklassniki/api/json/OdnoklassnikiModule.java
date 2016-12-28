package org.springframework.social.odnoklassniki.api.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.odnoklassniki.api.OdnoklassnikiProfile;

/**
 * Jackson module for setting up mixin annotations on Odnoklassniki model types.
 * This enables the use of Jackson annotations without
 * directly annotating the model classes themselves.
 *
 * @author vkolodrevskiy
 */
public class OdnoklassnikiModule  extends SimpleModule {
    private static final long serialVersionUID = -8727904672571822636L;

    public OdnoklassnikiModule() {
        super("OdnoklassnikiModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(OdnoklassnikiProfile.class, OdnoklassnikiProfileMixin.class);
    }
}
