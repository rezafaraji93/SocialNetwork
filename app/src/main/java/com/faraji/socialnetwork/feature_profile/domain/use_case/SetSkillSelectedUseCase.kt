package com.faraji.socialnetwork.feature_profile.domain.use_case

import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.util.Resource
import com.faraji.socialnetwork.core.util.UiText
import com.faraji.socialnetwork.feature_profile.domain.model.Skill
import com.faraji.socialnetwork.feature_profile.domain.util.ProfileConstants

class SetSkillSelectedUseCase{

    operator fun invoke(
        selectedSkills: List<Skill>,
        skillToToggle: Skill
    ): Resource<List<Skill>> {
        if (skillToToggle in selectedSkills) {
            return Resource.Success(selectedSkills - skillToToggle)
        }
        return if (selectedSkills.size >= ProfileConstants.MAX_SELECTED_SKILL_COUNT) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_max_skills_selected)
            )
        } else {
            Resource.Success(selectedSkills + skillToToggle)
        }
    }
}