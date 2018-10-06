package io.diaryofrifat.code.utils.helper

import java.util.*

class DataUtils private constructor() {
    companion object {
        /**
         * This method provides an unique id using UUID
         *
         * @return [String] unique string
         * */
        fun getUniqueId(): String {
            return java.util.UUID.randomUUID().toString()
        }

        /**
         * This method provides a random number
         *
         * @param min minimum limit
         * @param max maximum limit
         * @return [Int] random number
         * */
        fun randomInt(min: Int, max: Int): Int {
            return Random().nextInt(max - min + 1) + min
        }
    }
}