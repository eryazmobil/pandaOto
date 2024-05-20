package eryaz.software.panda.data.di

import eryaz.software.panda.data.repositories.AuthRepo
import eryaz.software.panda.data.repositories.BarcodeRepo
import eryaz.software.panda.data.repositories.CountingRepo
import eryaz.software.panda.data.repositories.OrderRepo
import eryaz.software.panda.data.repositories.PlacementRepo
import eryaz.software.panda.data.repositories.UserRepo
import eryaz.software.panda.data.repositories.WorkActivityRepo
import org.koin.dsl.module

val appModuleRepos = module {

    factory { AuthRepo(get()) }

    factory { UserRepo(get()) }

    factory { WorkActivityRepo(get()) }

    factory { BarcodeRepo(get()) }

    factory { PlacementRepo(get()) }

    factory { OrderRepo(get()) }

    factory { CountingRepo(get()) }

}