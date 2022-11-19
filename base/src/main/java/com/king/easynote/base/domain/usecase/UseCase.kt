package com.king.easynote.base.domain.usecase

/**
 * UseCase 基类
 *
 * @param P params 参数
 * @param R Result 结果
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
interface UseCase<P, R> {

    /**
     * 执行函数
     * @param params 参数
     * @return R 结果
     */
    suspend fun execute(params: P): R
}