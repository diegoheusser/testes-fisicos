(select
	distinct e.*,
	aeq.tempo as aeq
from
	Protocolo p inner join Execucao e on p.protocoloid = e.protocoloid
	inner join AgilidadeEmQuadra aeq on e.execucaoid = aeq.execucaoid
where 
	p.protocoloid = 3)
union
(select
	distinct e.*,
	sr.tempo as sr
from
	Protocolo p inner join Execucao e on p.protocoloid = e.protocoloid
	inner join ShuttleRun sr on e.execucaoid = sr.execucaoid
where
	p.protocoloid = 3)
union
(select
	distinct e.*,
	fop.distancia as fop
from
	Protocolo p inner join Execucao e on p.protocoloid = e.protocoloid
	inner join flexibilidade_de_ombro_e_punho fop on fop.flexibilidade_de_ombro_e_punho_id = e.execucaoid
where
	p.protocoloid = 3)
union
(select
	distinct e.*,
	lbp.quantidade as lbp
from 
	Protocolo p inner join Execucao e on p.protocoloid = e.protocoloid
	inner join lancamento_de_bolas_na_parede lbp on lbp.lancamento_de_bolas_na_parede_id = e.execucaoid
where
	p.protocoloid = 3)
union
(select
	distinct e.*,
	ra.quantidade as ra
from
	Protocolo p inner join Execucao e on p.protocoloid = e.protocoloid
	inner join repeticao_de_agachamento ra on ra.repeticao_de_agachamento_id = e.execucaoid
where
	p.protocoloid = 3)
union
(select
	distinct e.*,
	c.tempo as c
from
	Protocolo p inner join Execucao e on p.protocoloid = e.protocoloid
	inner join cegonha c on c.cegonha_id = e.execucaoid
where 
	p.protocoloid = 3)
union
(select
	distinct e.*,
	mc.completo as mc
from
	Protocolo p inner join Execucao e on p.protocoloid = e.protocoloid
	inner join musculatura_do_core mc on mc.musculatura_do_core_id = e.execucaoid
where
	p.protocoloid = 3)
union
(select
	distinct e.*,
	sv.distancia as sv
from 
	Protocolo p inner join Execucao e on p.protocoloid = e.protocoloid
	inner join salto_vertical sv on sv.salto_vertical_id = e.execucaoid
where 
	p.protocoloid = 3)
union
(select
	distinct e.*,
	sh.distancia as sh
from
	Protocolo p inner join Execucao e on p.protocoloid = e.protocoloid
	inner join salto_horizontal sh on sh.salto_horizontal_id = e.execucaoid
where
	p.protocoloid = 3)
union
(select
	distinct e.*,
	h.tempo as h
from
	Protocolo p inner join Execucao e on p.protocoloid = e.protocoloid
	inner join hexagono h on h.hexagono_id = e.execucaoid
where
	p.protocoloid = 3)
union
(select
	distinct e.*,
	trr.milisegundos as trr
from 
	Protocolo p inner join Execucao e on p.protocoloid = e.protocoloid
	inner join tempo_reacao_regua trr on trr.tempo_reacao_regua_id = e.execucaoid
where
	p.protocoloid = 3)
union
(select
	distinct e.*,
	sa.distancia as sa
from
	Protocolo p inner join Execucao e on p.protocoloid = e.execucaoid
	inner join sentar_alcancar sa on sa.sentar_alcancar_id = e.execucaoid
where
	p.protocoloId = 3);