package com.blues

class KotlinDemoClass {

    val test1: KotlinGenericClass<out Food> = KotlinGenericClass<Apple>()

    val test2: KotlinGenericClass<in Apple> = KotlinGenericClass<Food>()

    val test3: KotlinGenericInterface<out Food> = object : KotlinGenericInterface<Apple> {
        override fun method(): Apple {
            return Apple()
        }
    }

    val test4:KotlinGenericInterface<in  Apple> = object : KotlinGenericInterface<Food> {
        override fun method(): Food {
            return Banana()
        }
    }
}