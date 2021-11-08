package com.faraji.socialnetwork.feature_profile.domain.use_case

import com.faraji.socialnetwork.core.util.Resource
import com.faraji.socialnetwork.feature_profile.domain.model.Skill
import com.faraji.socialnetwork.feature_profile.domain.repository.ProfileRepository

class GetSkillsUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(): Resource<List<Skill>> {
        return repository.getSkills()
    }
}