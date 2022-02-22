import {TypedUseSelectorHook, useDispatch, useSelector} from 'react-redux'
import type {AppDispatch, RootState} from '../store/Store'

export const useAppDispatch = () => useDispatch<AppDispatch>()

export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector

export const useProcessError = () => ({error}: { error: any }): { message: string, type: string } => {
    if (error.isAxiosError) return error.response.data
    return error
}